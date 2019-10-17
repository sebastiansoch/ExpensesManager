package com.gmail.sebastiansoch.expensemanager;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.gmail.sebastiansoch.expensemanager.data.Category;
import com.gmail.sebastiansoch.expensemanager.data.CategoryGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CategoryExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private Map<CategoryGroup, List<Category>> allCategoriesForSettings;
    private List<CategoryGroup> categoryGroupList;
    private CategoryExpandableListListener categoryExpandableListListener;

    public CategoryExpandableListAdapter(Context context, Map<CategoryGroup, List<Category>> allCategoriesForSettings) {
        this.context = context;
        this.allCategoriesForSettings = allCategoriesForSettings;
        this.categoryGroupList = new ArrayList<>(allCategoriesForSettings.keySet());
        this.categoryExpandableListListener = (CategoryExpandableListListener) context;
    }

    @Override
    public int getGroupCount() {
        return this.allCategoriesForSettings.keySet().size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.allCategoriesForSettings.get(this.categoryGroupList.get(groupPosition)).size();
    }

    @Override
    public CategoryGroup getGroup(int groupPosition) {
        return this.categoryGroupList.get(groupPosition);
    }

    @Override
    public Category getChild(int groupPosition, int childPosition) {
        return this.allCategoriesForSettings.get(this.categoryGroupList.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, final boolean isExpanded, View convertView, ViewGroup parent) {

        final ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.view_settings_category_group, null);

            holder = new ViewHolder();
            holder.vhCategoryGroupChb = convertView.findViewById(R.id.settingsCategoryGroupChb);
            holder.vhCategoryGroupTV = convertView.findViewById(R.id.settingsCategoryGroupTV);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.vhCategoryGroupTV.setTypeface(null, Typeface.BOLD_ITALIC);
        holder.vhCategoryGroupTV.setText(getGroup(groupPosition).getName());
        holder.vhCategoryGroupTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryExpandableListListener.expandCategoryGroupEvent(groupPosition, isExpanded);
            }
        });
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.view_settings_category, null);

            holder = new ViewHolder();
            holder.vhCategoryChb = convertView.findViewById(R.id.settingsCategoryChb);
            holder.vhCategoryTV = convertView.findViewById(R.id.settingsCategoryTV);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.vhCategoryTV.setText(getChild(groupPosition, childPosition).getName());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class ViewHolder {
        public CheckBox vhCategoryGroupChb;
        public TextView vhCategoryGroupTV;
        public CheckBox vhCategoryChb;
        public TextView vhCategoryTV;
    }
}

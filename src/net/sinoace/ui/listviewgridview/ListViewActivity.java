package net.sinoace.ui.listviewgridview;

import java.util.List;

import net.sinoace.anim.R;

import android.app.Activity;
import android.os.Bundle;

import android.widget.ExpandableListView;

/**
 * ˽��
 * 
 * @author Administrator
 * 
 */
public class ListViewActivity extends Activity
{
	ExpandableListView expandableListView;

	ListViewAdapter treeViewAdapter;

	public String[] groups = { "�б�1", "�б�2", "�б�3" };

	public String[][] child = { { "" }, { "" }, { "", "" } };

	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listviewgridview_main);

		treeViewAdapter = new ListViewAdapter(this,
				ListViewAdapter.PaddingLeft >> 1);
		expandableListView = (ExpandableListView) this
				.findViewById(R.id.expandableListView);

		List<ListViewAdapter.TreeNode> treeNode = treeViewAdapter.GetTreeNode();
		for (int i = 0; i < groups.length; i++)
		{
			ListViewAdapter.TreeNode node = new ListViewAdapter.TreeNode();
			node.parent = groups[i];
			for (int ii = 0; ii < child[i].length; ii++)
			{
				node.childs.add(child[i][ii]);
			}
			treeNode.add(node);
		}

		treeViewAdapter.UpdateTreeNode(treeNode);
		expandableListView.setAdapter(treeViewAdapter);
	}
}
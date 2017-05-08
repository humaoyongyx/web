package issac.demo.dto;

import java.util.List;

import issac.demo.model.MenuBean;

public class TreeViewResult {

	private List<MenuBean> data;
	private String backColor = "#2f4050";
	private String color = "white";
	private String collapseIcon = "";
	private String expandIcon = "";
	private int levels = 1;
	private String nodeIcon;
	private String onhoverColor = "green";
	private int showBorder = 0;
	private int showTags = 1;

	public List<MenuBean> getData() {
		return data;
	}

	public void setData(List<MenuBean> data) {
		this.data = data;
	}

	public String getBackColor() {
		return backColor;
	}

	public void setBackColor(String backColor) {
		this.backColor = backColor;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getCollapseIcon() {
		return collapseIcon;
	}

	public void setCollapseIcon(String collapseIcon) {
		this.collapseIcon = collapseIcon;
	}

	public String getExpandIcon() {
		return expandIcon;
	}

	public void setExpandIcon(String expandIcon) {
		this.expandIcon = expandIcon;
	}

	public int getLevels() {
		return levels;
	}

	public void setLevels(int levels) {
		this.levels = levels;
	}

	public String getNodeIcon() {
		return nodeIcon;
	}

	public void setNodeIcon(String nodeIcon) {
		this.nodeIcon = nodeIcon;
	}

	public String getOnhoverColor() {
		return onhoverColor;
	}

	public void setOnhoverColor(String onhoverColor) {
		this.onhoverColor = onhoverColor;
	}

	public int getShowBorder() {
		return showBorder;
	}

	public void setShowBorder(int showBorder) {
		this.showBorder = showBorder;
	}

	public int getShowTags() {
		return showTags;
	}

	public void setShowTags(int showTags) {
		this.showTags = showTags;
	}


}

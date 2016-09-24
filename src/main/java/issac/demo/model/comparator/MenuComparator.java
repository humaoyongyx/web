package issac.demo.model.comparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import issac.demo.model.MenuBean;

public class MenuComparator implements Comparator<MenuBean> {

	@Override
	public int compare(MenuBean o1, MenuBean o2) {

		return o1.getOrderNo() - o2.getOrderNo();
	}

	public static void main(String[] args) {
		List<MenuBean> list = new ArrayList<>();
		MenuBean bean1 = new MenuBean();
		bean1.setOrderNo(3);
		list.add(bean1);
		MenuBean bean2 = new MenuBean();
		bean2.setOrderNo(1);
		list.add(bean2);

		MenuBean bean3 = new MenuBean();
		bean3.setOrderNo(6);
		list.add(bean3);
		System.out.println(list);
		Collections.sort(list, new MenuComparator());
		System.out.println(list);
	}

}

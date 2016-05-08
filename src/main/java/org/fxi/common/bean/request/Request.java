package org.fxi.common.bean.request;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class Request {

	private String userId;
	
	private String token;
	
	private Page page;
	
	private List<Order> sort;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public List<Order> getSort() {
		return sort;
	}
	public void setSort(List<Order> sort) {
		this.sort = sort;
	}

	public Pageable getPageable() {
		if (page == null) return null;
		return new Pageable() {

			@Override
			public int getPageNumber() {
				return page.num;
			}

			@Override
			public int getPageSize() {
				return page.size;
			}

			@Override
			public int getOffset() {
				return page.num * page.size;
			}

			@Override
			public Sort getSort() {
				if (sort == null) return null;
				if (sort.isEmpty()) return null;
				List<Sort.Order> orders = new ArrayList<>();
				for (Order o : sort) {
					Sort.Order order = new Sort.Order(Sort.Direction.fromString(o.getDirection()), o.getProperty());
					orders.add(order);
				}
				return new Sort(orders);
			}
			
		};
	}

	public static class Page {
		
		private int size;
		private int num;
		
		public int getSize() {
			return size;
		}
		public void setSize(int size) {
			this.size = size;
		}
		public int getNum() {
			return num;
		}
		public void setNum(int num) {
			this.num = num;
		}
		
	}
	
	public static class Order {
		
		public final static String DIRECTION_ASC = "ASC";
		public final static String DIRECTION_DESC = "DESC";
		
		private String direction;
		private String property;
		
		public String getDirection() {
			return direction;
		}
		public void setDirection(String direction) {
			this.direction = direction;
		}
		public String getProperty() {
			return property;
		}
		public void setProperty(String property) {
			this.property = property;
		}
	
	}
}

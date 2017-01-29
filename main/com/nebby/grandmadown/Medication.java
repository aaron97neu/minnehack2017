package com.nebby.grandmadown;

import java.util.Date;

public class Medication {

		private String name;
		private Date date;
		
		public Medication(String n, Date time) {
			name = n;
			date = time;			
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Date getDate() {
			return date;
		}

		public void setDate(Date date) {
			this.date = date;
		}
		
}

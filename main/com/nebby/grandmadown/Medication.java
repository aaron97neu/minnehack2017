package com.nebby.grandmadown;

import java.util.Date;

public class Medication {

		private String name;
		private String time;
		
		public Medication() {
			name = null;
			time= null;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getTime() {
			return time;
		}

		public void setTime(String date) {
			this.time = date;
		}
		
}

import java.net.URL;

import javax.lang.model.element.Element;
import javax.swing.ImageIcon;


public class myIcon extends ImageIcon {
		
		private String type;
		private String color;
		
		
		public myIcon(URL iconPlace,String type, String color) {
			super(iconPlace);
			setType(type);
			setColor(color);
		}


		public String getType() {
			return type;
		}


		public void setType(String type) {
			this.type = type;
		}


		public String getColor() {
			return color;
		}


		public void setColor(String color) {
			this.color = color;
		}
		
				

	}




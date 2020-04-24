package ui.guiforcase4;

import javax.swing.*;
import java.awt.*;

class PanelComboBoxCellRenderer implements ListCellRenderer{

	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index , boolean isSelected , boolean cellHasFocus ) {
	if(value instanceof JPanel){
		if(isSelected){
			((JPanel)value).setBackground(Color.LIGHT_GRAY);
		}else{
			((JPanel)value).setBackground(Color.white);
		}
		return (JPanel)value;
	}
		return null;
	}

}
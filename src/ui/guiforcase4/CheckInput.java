package ui.guiforcase4;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CheckInput extends PlainDocument {
  private int limit;
  private double minValue = 0;
  private boolean isMinValue = false;

  public CheckInput() {
    super();

  }

  public void setLength(int limit) {
    this.limit = limit;
  }

  public void setMinValue(double minValue) {
    this.isMinValue = true;
    this.minValue = minValue;
  }

//  public void insertString(int offs, String str, AttributeSet attr) throws BadLocationException, NumberFormatException {
//
//    if (str == null) {
//      return;
//    }
//    char[] upper = str.toCharArray();
//    if ((getLength() + str.length()) <= limit) {
//      int length = 0;
//      for (int i = 0; i < upper.length; i++) {
//        if (upper[i] >= '0' && upper[i] <= '9') {
//          upper[length++] = upper[i];
//        }
//      }
//      super.insertString(offs, new String(upper,0,length), attr);
//    }
//    super.insertString(offs, new String(), attr);
//  }
  @Override
  public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
    StringBuffer tmp = new StringBuffer(super.getText(0, super.getLength()));
    tmp.insert(offs, str);
    Pattern p = Pattern.compile("^-?\\d*(\\.)?\\d*$");
    Matcher m = p.matcher(tmp.toString());
    if (m.find()) {
      super.insertString(offs, str, a);
    } else
      Toolkit.getDefaultToolkit().beep();
  }
}



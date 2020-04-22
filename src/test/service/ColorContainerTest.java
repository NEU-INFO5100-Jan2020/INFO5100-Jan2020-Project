package service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ColorContainerTest {
  ColorContainer colorContainer;
  @BeforeEach
  void init(){
    colorContainer = new ColorContainer();
  }
  @Test
  void populateContainer() {
    colorContainer.populateContainer();
    for (Map.Entry<String ,String > entry:colorContainer.colors.entrySet()) {
      System.out.println(entry.getKey());
      System.out.println(entry.getValue());
    }
  }
}
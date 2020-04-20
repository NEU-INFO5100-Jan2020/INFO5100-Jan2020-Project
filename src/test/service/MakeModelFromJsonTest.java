package service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MakeModelFromJsonTest {
  @Test void test(){
    MakeModelFromJson makeModelFromJson = new MakeModelFromJson();
    makeModelFromJson.populateMakeModel();
  }

}
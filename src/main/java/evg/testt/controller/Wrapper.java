package evg.testt.controller;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by DENNNN on 26.11.2016.
 */
public class Wrapper {

    List<TestEntity> testEntityList;
    boolean flag;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public List<TestEntity> getTestEntityList() {
        return testEntityList;
    }

    public void setTestEntityList(List<TestEntity> testEntityList) {
        this.testEntityList = testEntityList;
    }

    public Wrapper() {
        testEntityList = new LinkedList<>();
        testEntityList.add(new TestEntity(11, "wwww"));
        testEntityList.add(new TestEntity(12, "aaa"));
    }
}

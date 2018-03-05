package org.elsys.ip.rest.service;

import org.elsys.ip.rest.model.Test;
import org.elsys.ip.rest.repository.TestRepository;
import org.elsys.ip.rest.repository.TestRepositoryJDBC;

import java.util.List;

public class TestService {

  private TestRepositoryJDBC testRepository = new TestRepositoryJDBC();

  public List<Test> getTestList() {
    return testRepository.getTestList();
  }

  public Test getTestById(Integer id) {
    return testRepository.getTestById(id).orElse(null);
  }

  public Test saveTest(Test test) {
    return testRepository.saveTest(test);
  }

  public Test updateTest(Integer id, Test test) {
    return testRepository.updateTest(id, test);
  }

  public void deleteTest(Integer id) {
    testRepository.deleteTest(id);
  }

  public void addTest(){ testRepository.addTest(); }
}

package com.harishkannarao.springboot.gradledemo.test;

import com.harishkannarao.springboot.gradledemo.common.AbstractBaseIntTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(profiles = {"local","integration-test", "integration-test-feature-toggle"})
public abstract class AbstractFeatureToggleIntTest extends AbstractBaseIntTest {

}

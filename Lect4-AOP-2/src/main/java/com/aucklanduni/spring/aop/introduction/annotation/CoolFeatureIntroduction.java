package com.aucklanduni.spring.aop.introduction.annotation;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;

//Declare this class as an Aspect so that it will be detected by <aop:aspectj-autoproxy />.
@Aspect
public class CoolFeatureIntroduction {

	// Specify properties of this Introduction:
	// - value The target object class.
	// - defaultImpl The class that implements the new interface being introduced.
	// The @DeclareParents annotation decorates a field whose type specifies the interface 
	// being introduced.
	@DeclareParents(
			value = "com.aucklanduni.spring.aop.introduction.annotation.TargetBeanImpl",
			defaultImpl = CoolFeatureImpl.class)
	public CoolFeature _introducedInterface;
}

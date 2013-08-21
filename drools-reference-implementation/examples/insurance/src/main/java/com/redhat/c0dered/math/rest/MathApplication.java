package com.redhat.c0dered.math.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;
import javax.ws.rs.ApplicationPath;

public class MathApplication extends Application{

		private Set<Object> singletons = new HashSet<Object>();
		private Set<Class<?>> empty = new HashSet<Class<?>>();
		public MathApplication(){
		     singletons.add(new MathService());
		}
		@Override
		public Set<Class<?>> getClasses() {
		     return empty;
		}
		@Override
		public Set<Object> getSingletons() {
		     return singletons;
		}
}

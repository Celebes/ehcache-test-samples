package io.github.celebes.ehcache.test.samples.replicated.model;

import java.io.Serializable;
import java.util.Map;

public class Foo implements Serializable {
	private static final long serialVersionUID = 3685667918638899296L;

	private Long fooId;
	private String fooName;

	public Foo() {

	}

	public String getFooName() {
		return fooName;
	}

	public void setFooName(String fooName) {
		this.fooName = fooName;
	}

	@Override
	public String toString() {
		return "Foo, id: " + getId() + ", name: " + getFooName();
	}

	public long getId() {
		return fooId.intValue();
	}

	public void setId(long id) {
		fooId = id;
	}
}

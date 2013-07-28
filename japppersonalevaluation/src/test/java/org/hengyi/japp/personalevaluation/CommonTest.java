package org.hengyi.japp.personalevaluation;

import java.util.Set;

import org.junit.Test;

import com.google.common.collect.Sets;
import com.hengyi.japp.personalevaluation.domain.node.Person;

public class CommonTest {
	@Test
	public void test() {
		Person person1 = new Person();
		Person person2 = new Person();
		System.out.println(person1 == person2);
		System.out.println(person1.hashCode());
		System.out.println(person2.hashCode());
		System.out.println(person1.equals(person2));

		Set<Person> persons = Sets.newHashSet();
		for (int i = 0; i < 3; i++) {
			Person person = new Person();
			persons.add(person);
		}
		System.out.println(persons);
	}
}

package com.example.db;

import java.util.List;

import com.example.domain.Person;
import com.example.service.OtherPersonService;
import com.example.service.PersonService;

import android.test.AndroidTestCase;
import android.util.Log;

public class PersonServiceTest extends AndroidTestCase {
	private static String TAG = "PersonServiceTest";

	// OtherPersonService personService = new
	// OtherPersonService(this.getContext());
	// //不可以这么写，因为Android把context环境变量是在PersonServiceTest实例化后给他的

	public void testSave() throws Exception {
		PersonService personService = new PersonService(this.getContext());
		// personService.save(new Person("老猪", (short) 11));
		for (int i = 0; i < 10; i++) {
			personService.save(new Person("你" + i, (short) (i + 10)));
		}

	}

	public void testFind() throws Exception {
		PersonService personService = new PersonService(this.getContext());
		Person person = personService.find(1);
		Log.i(TAG, person.toString());
	}

	public void testUpdate() throws Exception {
		PersonService personService = new PersonService(this.getContext());
		Person person = personService.find(1);
		person.setName("lv");
		personService.update(person);
	}

	public void testDelete() throws Exception {
		PersonService personService = new PersonService(this.getContext());
		personService.delete(1, 2, 3);
	}

	public void testGetCount() throws Exception {
		PersonService personService = new PersonService(this.getContext());
		Log.i(TAG, String.valueOf(personService.getCount()));
	}

	public void testGetScrollData() throws Exception {
		PersonService personService = new PersonService(this.getContext());
		List<Person> persons = personService.getScrollData(0, 40);
		for (Person person : persons) {
			Log.i(TAG, person.toString());
		}
	}
}

package com.humbertdany.tpproject.util.generator;

import java.security.SecureRandom;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class who generate random strings
 */
public class RandomStringGenerator {

	/**
	 * @see : http://stackoverflow.com/questions/39222044/generate-random-string-in-java
	 * @param codeLength : how long the string should be
	 * @return the generated strings
	 */
	public static String gen(int codeLength) {
		List<Character> temp = "abcdefghijklmnopqrstuvwxyz".chars()
				.mapToObj(i -> (char)i)
				.collect(Collectors.toList());
		Collections.shuffle(temp, new SecureRandom());
		return temp.stream()
				.map(Object::toString)
				.limit(codeLength)
				.collect(Collectors.joining());
	}

}

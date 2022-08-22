package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class PokerHandTest {

	@Test
	public void testIllegalInstantiation () {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new PokerHand("2C"));
		Assertions.assertThrows(IllegalArgumentException.class, () -> new PokerHand("2C 3H 4Z TD JH"));
		Assertions.assertThrows(IllegalArgumentException.class, () -> new PokerHand("2C 3H 4S TD QC JH"));
		Assertions.assertThrows(IllegalArgumentException.class, () -> new PokerHand("2C 3H 4S 1D JH"));
	}

	@Test
	public void testLegalInstantiation () {
		List<Character> nominalChars = Arrays.asList('2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A');
		List<Character> suitChars = Arrays.asList('S', 'H', 'D', 'C');

		for (Character nominalChar : nominalChars) {
			for (Character suitChar : suitChars) {
				String cardChar = nominalChar+""+suitChar;
				Assertions.assertDoesNotThrow(() -> new PokerHand(cardChar+" "+cardChar+" "+cardChar+" "+cardChar+" "+cardChar));
			}
		}
	}

	@Test
	public void testComparison () {
		assertThatFirstIsWinner(new PokerHand("2S 3H 4D 5C 7C"), new PokerHand("2S 3H 4D 5C 6C"));
		assertThatFirstIsWinner(new PokerHand("2S 2H 2D 2C AC"), new PokerHand("2S 2H 2D 3C AC"));
		assertThatFirstIsWinner(new PokerHand("TS JH QD KC AC"), new PokerHand("QS QH QD 3C 2C"));
	}

	@Test
	public void testSorting () {
		PokerHand expected;
		List<PokerHand> hands;

		hands = new ArrayList<>();
		hands.add(			 new PokerHand("KS 2H 5C JD TD"));
		hands.add(expected = new PokerHand("2C 3C AC 4C 5C"));
		Collections.sort(hands);
		Assertions.assertSame(expected, hands.get(0));

		hands = new ArrayList<>();
		hands.add(			 new PokerHand("KS 2H 5C JD TD"));
		hands.add(expected = new PokerHand("KS 3H 3C 3D 3D"));
		hands.add(			 new PokerHand("KS KH 5C QD QD"));
		hands.add(			 new PokerHand("2C 3C AC 4C 5C"));
		Collections.sort(hands);
		Assertions.assertSame(expected, hands.get(0));
	}

	private static void assertThatFirstIsWinner(PokerHand first, PokerHand second) {
		Assertions.assertTrue(first.compareTo(second) < 0);
	}
}
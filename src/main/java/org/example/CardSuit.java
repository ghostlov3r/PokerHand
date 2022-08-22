package org.example;

/**
 * Масть игральной (покерной) карты.
 *
 * @see PokerCard
 */
public enum CardSuit {
	SPADES,
	HEARTS,
	DIAMONDS,
	CLUBS;

	public static CardSuit parse (char suit) {
		switch (suit) {
			case 'S':
				return SPADES;
			case 'H':
				return HEARTS;
			case 'D':
				return DIAMONDS;
			case 'C':
				return CLUBS;
		}

		throw new IllegalArgumentException("Unknown card suit '"+suit+"'");
	}
}

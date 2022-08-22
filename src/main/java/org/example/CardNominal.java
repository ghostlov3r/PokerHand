package org.example;

/**
 * Номинал игральной (покерной) карты.
 *
 * @see PokerCard
 */
public enum CardNominal {
	TWO,    // 1
	THREE,    // 10
	FOUR,    // 100
	FIVE,    // 1000
	SIX,    // 10000
	SEVEN,    // 100000
	EIGHT,    // 1000000
	NINE,    // 10000000
	TEN,   // 100000000
	JACK,  // 1000000000
	QUEEN, // 10000000000
	KING,  // 100000000000
	ACE;   // 1000000000000

	public static CardNominal parse (char value) {
		switch (value) {
			case '2':
				return CardNominal.TWO;
			case '3':
				return CardNominal.THREE;
			case '4':
				return CardNominal.FOUR;
			case '5':
				return CardNominal.FIVE;
			case '6':
				return CardNominal.SIX;
			case '7':
				return CardNominal.SEVEN;
			case '8':
				return CardNominal.EIGHT;
			case '9':
				return CardNominal.NINE;
			case 'T':
				return CardNominal.TEN;
			case 'J':
				return CardNominal.JACK;
			case 'Q':
				return CardNominal.QUEEN;
			case 'K':
				return CardNominal.KING;
			case 'A':
				return CardNominal.ACE;
		}

		throw new IllegalArgumentException("Unknown card nominal '"+value+"'");
	}
}

package org.example;

/**
 * Этот простой класс представляет собой некую игральную (покерную) карту.
 */
public class PokerCard {

	public static PokerCard parse (String card) {
		if (card.length() != 2) {
			throw new IllegalArgumentException(2 +" length expected, got "+card.length());
		}

		char nominal = card.charAt(0);
		char suit = card.charAt(1);

		return new PokerCard(CardNominal.parse(nominal), CardSuit.parse(suit));
	}

	/**
	 * Номинал данной карты.
	 */
	private final CardNominal nominal;

	/**
	 * Масть данной карты.
	 */
	private final CardSuit suit;

	public PokerCard (CardNominal nominal, CardSuit suit) {
		this.nominal = nominal;
		this.suit = suit;
	}

	/**
	 * Возвращает номинал данной карты.
	 */
	public CardNominal getNominal() {
		return nominal;
	}

	/**
	 * Возвращает масть данной карты.
	 */
	public CardSuit getSuit() {
		return suit;
	}
}

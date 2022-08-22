package org.example;

/**
 * Hand values.
 *
 * <p>The following enum shows the possible hand values in increasing order.
 *
 * <p>From https://en.wikipedia.org/wiki/Texas_hold_%27em
 */
public enum HandCombination {
	/**
	 * Simple value of the card.
	 * Lowest: 2 – Highest: Ace (King in example)
	 */
	HIGHCARD,

	/**
	 * Two cards with the same value
	 */
	PAIR,

	/**
	 * Two times two cards with the same value
	 */
	TWO_PAIRS,

	/**
	 * Three cards with the same value
	 */
	THREE_OF_A_KIND,

	/**
	 * Sequence of 5 cards in increasing value (Ace can precede 2 and follow up King)
	 */
	STRAIGHT,

	/**
	 * 5 cards of the same suit
	 */
	FLUSH,

	/**
	 * Combination of three of a kind and a pair
	 */
	FULL_HOUSE,

	/**
	 * Four cards of the same value
	 */
	FOUR_OF_A_KIND,

	/**
	 * Straight of the same suit
	 */
	STRAIGHT_FLUSH,

	/**
	 * Straight flush from Ten to Ace
	 */
	ROYAL_FLUSH
}

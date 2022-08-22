package org.example;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * <p>Класс для ранжирования покерных рук, представляет собой набор карт в руке "Техасского холдема".
 *
 * <p>Основная задача класса - сравнение несколькоих рук для выяснения победителя в игре.
 */
public class PokerHand implements Comparable<PokerHand> {

	public static final int HAND_LENGTH = 5;

	/**
	 * Входная строка с данными.
	 */
	private final String input;

	/**
	 * Комбинация, получившаяся в данной руке
	 */
	private HandCombination combination;

	/**
	 * Сила карт в данной руке, используется для определения победителя если у них сложились одинаковые комбинации.
	 */
	private long power;

	/**
	 * Конструирует новый объект руки "Техасского холдема",
	 * принимает на вход строку, содержащую пять карт:
	 *
	 * <p> {@code PokerHand hand = new PokerHand("KS 2H 5C JD TD"); }
	 *
	 * <p>В качестве разделителя входной строки используется пробел.
	 * <p>Описание каждой карты во входной строке состоит из двух символов:
	 * <li>первый символ — это номинал карты. Допустимые значения: 2, 3, 4, 5, 6, 7, 8, 9, T(en),
	 *  J(ack), Q(ueen), K(ing), A(ce);
	 * <li>второй символ — масть. Допустимые значения: S(pades), H(earts), D(iamonds), C(lubs).
	 */
	public PokerHand (String cards) {
		this.input = cards;

		String[] split = cards.split(Pattern.quote(" "));
		if (split.length != HAND_LENGTH) {
			throw new IllegalArgumentException(HAND_LENGTH +" cards expected, got "+ split.length+", input: "+cards);
		}

		calculatePower(Arrays.stream(split)
				.map(PokerCard::parse)
				.sorted(Comparator.comparing(PokerCard::getNominal))
				.collect(Collectors.toList()));
	}

	/**
	 * Выясняет, какой рейтинг имеет данная рука.
	 *
	 * <p>В ТЗ сказано не учитывать масть,
	 * поэтому рука не может иметь рейтинги {@link HandCombination#FLUSH}, {@link HandCombination#STRAIGHT_FLUSH} и {@link HandCombination#ROYAL_FLUSH}
	 */
	private void calculatePower (List<PokerCard> cards) {
		Map<CardNominal, Integer> counts = new EnumMap<>(CardNominal.class);

		// ТЗ: "Для упрощения считать, что туз в комбинациях
		// стрит или стрит-флэш может быть только наивысшей картой."
		boolean acePresent = false;

		// Соотносим номиналы карт и их количество в руке
		for (PokerCard card : cards) {
			counts.merge(card.getNominal(), 1, Integer::sum);

			if (card.getNominal() == CardNominal.ACE) {
				acePresent = true;
			}
		}

		boolean four = false;
		boolean three = false;
		int pairs = 0;

		for (Integer count : counts.values()) {
			if (count == 4) {
				four = true;
			}
			else if (count == 3) {
				three = true;
			}
			else if (count == 2) {
				++pairs;
			}
		}

		// Присутствует 4 одинаковых номинала - комбинация Four of a kind
		if (four) {
			combination = HandCombination.FOUR_OF_A_KIND;
		}
		// Присутствует 3 одинаковых номинала и пара
		else if (three && pairs >= 1) {
			combination = HandCombination.FULL_HOUSE;
		}
		else if (acePresent && isStraight(cards)) {
			combination = HandCombination.STRAIGHT;
		}
		// Только 3
		else if (three) {
			combination = HandCombination.THREE_OF_A_KIND;
		}
		// Присутствуют 2 пары - комбинация 2 + 2
		else if (pairs >= 2) {
			combination = HandCombination.TWO_PAIRS;
		}
		// Только 1 пара
		else if (pairs >= 1) {
			combination = HandCombination.PAIR;
		}
		// Наиболее проигрышный случай
		else {
			combination = HandCombination.HIGHCARD;
		}

		long power = 0;

		for (PokerCard card : cards) {
			long cardPower = 1;
			for (int i = 0; i < card.getNominal().ordinal(); i++) {
				cardPower *= 10;
			}
			power += cardPower;
		}

		this.power = power;
	}

	/**
	 * Метод помогает выяснить, присутствует ли в списке карт комбинация {@link HandCombination#STRAIGHT}
	 */
	private boolean isStraight (List<PokerCard> cards) {
		CardNominal first = cards.get(0).getNominal();

		return cards.get(1).getNominal().ordinal() == first.ordinal() + 1 &&
				cards.get(2).getNominal().ordinal() == first.ordinal() + 2 &&
				cards.get(3).getNominal().ordinal() == first.ordinal() + 3 &&
				cards.get(4).getNominal().ordinal() == first.ordinal() + 4;
	}

	/**
	 * Сравнивает "рейтинг" этой руки и другой руки "Техасского холдема".
	 *
	 * @param other Рука, с которой надо сравнить текущую руку.
	 *
	 * @return  1 если текущая рука слабее;
	 * 			0 если руки эквивалентны по "рейтингу";
	 * 		   -1 если текущая рука сильнее чем переданная.
	 */
	@Override
	public int compareTo (PokerHand other) {
		int tmp = other.combination.compareTo(combination);
		if (tmp != 0) {
			return tmp;
		}
		return Long.compare(other.power, power);
	}

	/**
	 * Возвращает строковое представление данной руки "Техасского холдема" в том же формате,
	 * который принимает конструктор этого класса.
	 *
	 * <p>Пример: "KS 2H 5C JD TD"
	 */
	@Override
	public String toString() {
		return input + " " + combination + " " + power;
	}
}

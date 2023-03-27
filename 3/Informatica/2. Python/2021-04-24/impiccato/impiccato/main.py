from impiccato.art import stages, logo
from impiccato.words import word_list
from random import choice

def main():
    print(logo)

    stage = 6
    word = choice(word_list)
    masked_word = ["_" for letter in range(len(word))]
    print(f"Word: {word}")
    while stage >= 0:
        print(stages[stage])
        print(" ".join(masked_word))
        print()
        letter_input = input("Inserisci una lettera: ")

        if len(letter_input) != 1:
            print("Inserisci una lettera!")
            continue

        if not letter_input in word:
            print("Errato")
            stage -= 1
            continue

        for i, letter in enumerate(word):
            if letter_input == letter:
                masked_word[i] = letter

        solved = True
        for letter in masked_word:
            if letter == "_":
                solved = False

        if solved:
           print("Hai vinto")
           break

    if stage < 0:
        print("Hai perso")

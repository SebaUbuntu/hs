#!/usr/bin/python3

from impiccato import module_path
from impiccato.art import stages, logo
from impiccato.words import word_list
from random import choice
from tkinter import *

def update_masked_word():
    tk_masked_word.config(text=" ".join(masked_word))

def update_image():
    tk_image.config(image=stages_images[stage])

def update(letter_input):
    global stage
    if not letter_input in word:
        tk_status.config(text="Errato")
        stage -= 1
        return

    for i, letter in enumerate(word):
        if letter_input == letter:
            masked_word[i] = letter

def process_char():
    global stage

    letter_input = tk_input.get()

    if len(letter_input) != 1:
        tk_status.config(text="Inserisci una lettera!")
        return
    
    update(letter_input)
    update_masked_word()
    update_image()

    if stage == 0:
        tk_status.config(text="Hai perso")
        tk_confirm.config(state=DISABLED)
    elif not "_" in masked_word:
        tk_status.config(text="Hai vinto")
        tk_confirm.config(state=DISABLED)

if __name__ == '__main__':
    tk = Tk()
    tk_image = Label(tk)
    tk_status = Label(tk)
    tk_masked_word = Label(tk)
    tk_input_des = Label(tk, text="Inserisci una lettera")
    tk_input = Entry(tk)

    tk_image.pack(side=TOP)
    tk_status.pack(side=TOP)
    tk_masked_word.pack(side=TOP)
    tk_input_des.pack(side=TOP)
    tk_input.pack(side=TOP)

    tk_image_0 = PhotoImage(file=f"{module_path}/png/0.png")
    tk_image_1 = PhotoImage(file=f"{module_path}/png/1.png")
    tk_image_2 = PhotoImage(file=f"{module_path}/png/2.png")
    tk_image_3 = PhotoImage(file=f"{module_path}/png/3.png")
    tk_image_4 = PhotoImage(file=f"{module_path}/png/4.png")
    tk_image_5 = PhotoImage(file=f"{module_path}/png/5.png")
    tk_image_6 = PhotoImage(file=f"{module_path}/png/6.png")

    stages_images = [
        tk_image_0,
        tk_image_1,
        tk_image_2,
        tk_image_3,
        tk_image_4,
        tk_image_5,
        tk_image_6
    ]

    stage = 6
    word = choice(word_list)
    masked_word = ["_" for letter in range(len(word))]
    print(f"Word: {word}")
    update_masked_word()
    update_image()

    tk_confirm = Button(tk, text="Invia", command=process_char)
    tk_confirm.pack(side=TOP)

    tk.mainloop()

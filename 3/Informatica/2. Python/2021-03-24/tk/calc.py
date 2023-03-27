from tkinter import *

tk = Tk()

label = Entry(tk, width=35)
label.pack(side=TOP)

buttons_frame = Frame(tk)
buttons_frame.pack(side=BOTTOM)

button0 = Button(buttons_frame, text=0, padx=30, pady=15, command=lambda: click_number(0))
button1 = Button(buttons_frame, text=1, padx=30, pady=15, command=lambda: click_number(1))
button2 = Button(buttons_frame, text=2, padx=30, pady=15, command=lambda: click_number(2))
button3 = Button(buttons_frame, text=3, padx=30, pady=15, command=lambda: click_number(3))
button4 = Button(buttons_frame, text=4, padx=30, pady=15, command=lambda: click_number(4))
button5 = Button(buttons_frame, text=5, padx=30, pady=15, command=lambda: click_number(5))
button6 = Button(buttons_frame, text=6, padx=30, pady=15, command=lambda: click_number(6))
button7 = Button(buttons_frame, text=7, padx=30, pady=15, command=lambda: click_number(7))
button8 = Button(buttons_frame, text=8, padx=30, pady=15, command=lambda: click_number(8))
button9 = Button(buttons_frame, text=9, padx=30, pady=15, command=lambda: click_number(9))

number = 0

def click_number(number: int):
    numero_corrente = label.get()
    label.delete(0, END)
    label.insert(0, f"{str(numero_corrente)}{str(number)}")

def click_clear():
    global number
    label.delete(0, END)
    number = 0

def click_plus():
    global number
    number += int(label.get())
    label.delete(0, END)

def click_equal():
    click_plus()
    label.insert(0, str(number))

buttonclear = Button(buttons_frame, text="C", padx=30, pady=15, command=click_clear)
buttonequal = Button(buttons_frame, text="=", padx=30, pady=15, command=click_equal)
buttonplus = Button(buttons_frame, text="+", padx=30, pady=15, command=click_plus)

buttons = [
    [button7, button8, button9],
    [button4, button5, button6],
    [button1, button2, button3],
    [button0],
]

next_row=0
for row in buttons:
    button_p = 0
    for button in row:
        button.grid(row=next_row, column=button_p)
        button_p += 1
    next_row += 1

buttonclear.grid(row=0, column=4)
buttonplus.grid(row=1, column=4, columnspan=2)
buttonequal.grid(row=3, rowspan=2, column=1)

tk.mainloop()

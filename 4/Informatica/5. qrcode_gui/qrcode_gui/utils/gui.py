from pathlib import Path
from qrcode_gui.utils.qr import QRCodeManager
from tkinter import Tk, Widget, Button, Entry, Label
from tkinter.filedialog import asksaveasfilename
from PIL import ImageTk
from typing import Union

DEFAULT_URL = "https://google.com"

class QRCodeGUI:
	def __init__(self):
		self.qrmanager = QRCodeManager()
		self.image = None

		self.root = Tk()
		self.root.title("QR Code Generator")
		self.root.resizable(False, False)

		self.url_entry_label = Label(text="URL:")
		self.url_entry = Entry(self.root)
		self.generate_button = Button(self.root, text="Genera", command=self.generate)
		self.image_label = Label(self.root, height=self.qrmanager.IMAGE_SIZE, width=self.qrmanager.IMAGE_SIZE)
		self.last_url_label = Label()
		self.save_button = Button(self.root, text="Salva come PNG...", command=self.save)

		self.ui_elements: list[list[tuple[Union[Widget, None], int]]] = [
			[(self.url_entry_label, 1), (self.url_entry, 1), (self.generate_button, 1)],
			[(self.image_label, 3),     (None, 1),           (None, 1)                ],
			[(self.last_url_label, 2),  (None, 1),           (self.save_button, 1)    ],
		]

		for row_n, rows in enumerate(self.ui_elements):
			for column_n, widget_info in enumerate(rows):
				widget, columnspan = widget_info
				if not widget:
					continue

				sticky = "NSEW"
				if column_n == len(rows) - 1:
					sticky = "E"
				if column_n == 0:
					sticky = "W"
				widget.grid(row=row_n, column=column_n, columnspan=columnspan, sticky=sticky)

		self.generate(DEFAULT_URL)

	def start(self):
		self.root.mainloop()

	def generate(self, url=None):
		if not url:
			url = self.url_entry.get()
		if not url:
			return

		url = f"https://{url}" if not (url.startswith("http://") or url.startswith("https://")) else url
		self.last_data = self.qrmanager.get_qrcode(url)
		self.image = ImageTk.PhotoImage(data=self.last_data)
		self.image_label.configure(image=self.image)
		self.last_url_label.configure(text=url)

	def save(self):
		path = asksaveasfilename(initialdir=Path.home(), defaultextension=".png", filetypes=[("PNG", "*.png")])
		Path(path).write_bytes(self.last_data)

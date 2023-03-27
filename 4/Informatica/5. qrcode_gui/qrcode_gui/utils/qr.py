from PIL.Image import Image
from qrcode import QRCode
from io import BytesIO

class QRCodeManager:
	IMAGE_SIZE = 300

	def __init__(self):
		self.qr = QRCode()

	def get_qrcode(self, data: str):
		self.qr.add_data(data)
		self.qr.make()

		img: Image = self.qr.make_image().get_image()
		img = img.resize((self.IMAGE_SIZE, self.IMAGE_SIZE))

		output = BytesIO()
		img.save(output, format='PNG')

		self.qr.clear()

		return output.getvalue()

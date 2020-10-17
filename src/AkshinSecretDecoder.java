import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Extracts both the first and second image from the secret image.
 */
public class AkshinSecretDecoder {
	private static final String INPUT_IMAGE = "imageembedded.png";
	private static final String OUTPUT_SECRET = "extracted_secret.png";
	private static final String OUTPUT_ORIGINAL = "extracted_original.png";

	public static void main(String[] args) {
		// pixel loop code based on:
		// https://stackoverflow.com/questions/10544277/

		// load local file
		BufferedImage image = loadImage();

		// extract secret
		extractSecret(image);

		// --- extra stuff
		// extract original
		extractOriginal(image);

	}

	private static BufferedImage loadImage() {
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File(INPUT_IMAGE));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}

	private static void extractSecret(BufferedImage image) {
		BufferedImage output = new BufferedImage(image.getWidth(), image.getHeight(),
				BufferedImage.TYPE_3BYTE_BGR);

		// loop through image
		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				// get original values
				int color = image.getRGB(x, y);
				int red = (color >> 16) & 0xff;
				int green = (color >> 8) & 0xff;
				int blue = color & 0xff;

				// modify values
				int sigRed = (red & 0b11) * 255;
				int sigGreen = (green & 0b11) * 255;
				int sigBlue = (blue & 0b11) * 255;
				int newColor = ((sigRed << 16) | (sigGreen << 8) | (sigBlue));
				// save values to output
				output.setRGB(x, y, newColor);

			}
		}

		saveFile(output, OUTPUT_SECRET);

		System.out.println("Extracted secret image!");
	}

	private static void extractOriginal(BufferedImage image) {
		BufferedImage output = new BufferedImage(image.getWidth(), image.getHeight(),
				BufferedImage.TYPE_3BYTE_BGR);

		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				// get original values
				int color = image.getRGB(x, y);
				int red = (color >> 16) & 0xff;
				int green = (color >> 8) & 0xff;
				int blue = color & 0xff;

				// modify values
				int sigRed = (red & 0xc0);
				int sigGreen = (green & 0xc0);
				int sigBlue = (blue & 0xc0);
				int newColor = ((sigRed << 16) | (sigGreen << 8) | (sigBlue));

				// save values to output
				output.setRGB(x, y, newColor);

			}
		}

		saveFile(output, OUTPUT_ORIGINAL);

		System.out.println("Extracted original image!");
	}

	private static void saveFile(BufferedImage secretImage, String path) {
		try {
			File output = new File(path);
			ImageIO.write(secretImage, "png", output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

# HackGT7_BlackRock_Mini_Challenge
My solution for BlackRock's Mini-Challenge at HackGT 7.

Extracts an embedded secret image from a source, where the encoded image is stored as the two most significant bits.

Challenge description (summarized):
Steganography is the practice of hiding a secret message inside of something that is not secret, like a "digital image" insider another "digital image".
A "Digital Image" is a dot matrix data structure that represents a grid of pixels, so each pixel is the smallest individual element of an image and provides variable color composed of three values (red, green and blue) which are 8 bit values or an integer (32 bit) pixel.
So each pixel can be represented with three values in binary code where the least significant bit is the rightmost bit and has less impact on the final value and the leftmost bit is the most significant bit, so that, changes on the rightmost bits will have a small visual impact on the digital image while any change on the leftmost bits will have a large impact on the final value.
Your mission (should you choose to accept it!) is to write the code that extracts and reveals the hidden secret image that was hidden inside of the BlackRock logo by using its 2 most significant bits.

# RFM69_LPC812_OTAFW

Use RFM69_LPC812 firmware (0.6.0+) capabilit of over the air firmware updates to update flash. Requires WebSocketUARTBridge to talk to radio via a TCP network port.

## Steps

 * Convert .bin file to .hex using BinToPage64Hex. This format is has one line records for each 64 byte page of flash. There are three fields per line: base address, flash content and CRC32 of page.
 * Send command to radio being updated to enter boot loader mode
 * (optional) Download current CRC32 of each page of remote flash
 * (optional) Create a list of flash pages that need update
 * Reprogram flash pages that need to be updated.
 * Request CRC32 of updates pages to ensure successful update. If not reprogram failed pages.
 * Send command to exit bootloader and reboot.

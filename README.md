This exploit works in a pretty simple way - by appending a visual  basic script to the end of an image file. The file is then cached to the disk when someone loads it in via a service such as Discord. When it's written to the disk, Windows Defender scans the file and detects the visual basic script as malware.
  
Credits:
  - Foggylight27 - Originally found this
  - Josephworks - Joseph's face was used to test this
  - Cystemz - Tried to make an iplogger & RCE out of this. Didn't work for Discord
  - AidanWD
  
This may also work for various other services, such as Zoom, Steam, Microsoft Teams, etc.

// Exercise 1.4.16
#include "stdio.h"
#include "stdlib.h"
#include "string.h"

char* new_file_name(const char* filename, const char* extension) {
	// +1 for the extra "." and +1 for the last \0
	char* new_name = malloc(strlen(filename) + strlen(extension) + 1 + 1);
	strcpy(new_name, filename);
	strcat(new_name, ".");
	strcat(new_name, extension);
	return new_name;
}


int main(int argc, char const *argv[])
{
	if (argc < 3)
	{
		printf("Usage: %s %s %s\n", argv[0], "filename", "extension");
		exit(1);
	}
	char* filename_ending = strchr(argv[1], '.');
	if(filename_ending != NULL) {
		*filename_ending = '\0';
	}
	char* new_name = new_file_name(argv[1], argv[2]);
	printf("New file name: %s\n", new_name);
	free(new_name);

	return 0;
}
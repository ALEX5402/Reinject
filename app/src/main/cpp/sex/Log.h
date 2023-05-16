#ifndef _CLog_h
#define _CLog_h
#include "Includes.h"

class CLog {
private:
	std::ofstream fs;
public:
	CLog() {}
	CLog(const char *szPath);
    void Open(const char* szPath);
    void Close();
	void Write(const char* szText, ...);
};
#endif
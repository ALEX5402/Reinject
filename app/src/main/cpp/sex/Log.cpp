#include "Log.h"

CLog::CLog(const char *szPath) {
    fs.open(szPath, std::ios::out);
}

void CLog::Open(const char* szPath){
    fs.open(szPath, std::ios::out);
}
void CLog::Close(){
    fs.close();
}


void CLog::Write(const char* szText, ...){
    char buf[1024];
    va_list va;
    va_start(va, szText);

    vsprintf(buf, szText, va);
    fs << buf << std::endl;
    va_end(va);
}
package com.emailapp.repository;

import com.emailapp.domain.FileEntity;

public interface FileRepository {

    void writeToFile(FileEntity fileEntity);
}

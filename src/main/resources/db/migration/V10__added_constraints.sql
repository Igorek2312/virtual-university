CREATE UNIQUE INDEX account_document_number_uindex
  ON account (document_number);
ALTER TABLE student MODIFY record_book_number VARCHAR(255) NOT NULL;
CREATE UNIQUE INDEX student_record_book_number_uindex ON student (record_book_number);
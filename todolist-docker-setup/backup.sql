CREATE TABLE task (
                      id VARCHAR(36) PRIMARY KEY,
                      title VARCHAR(255) NOT NULL,
                      description TEXT,
                      due_date DATE,
                      priority ENUM('LOW', 'MEDIUM', 'HIGH') NOT NULL DEFAULT 'MEDIUM',
                      status ENUM('PENDING', 'IN_PROGRESS', 'COMPLETED', 'OVERDUE', 'BLOCKED', 'CANCELLED') NOT NULL DEFAULT 'PENDING'
);

CREATE TABLE dependency (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            task_id VARCHAR(36),
                            dependency_id VARCHAR(36),
                            FOREIGN KEY (task_id) REFERENCES task (id) ON DELETE CASCADE,
                            FOREIGN KEY (dependency_id) REFERENCES task (id) ON DELETE CASCADE,
                            UNIQUE(task_id, dependency_id)
);

INSERT INTO task (id, title, description, due_date, priority, status) VALUES
                                                                          ('550e8400-e29b-41d4-a716-446655440000', 'Meeting with Team', 'Discuss project progress', '2025-03-25', 'HIGH', 'PENDING'),
                                                                          ('d2c8b8a1-4f2b-42de-b69f-2c62f8bb32c9', 'Code Review', 'Review pull requests from developers', '2025-03-22', 'MEDIUM', 'IN_PROGRESS'),
                                                                          ('a58b69f4-8f5e-42a2-91eb-31a51d986ba2', 'Write Documentation', 'Complete API documentation', '2025-03-28', 'LOW', 'PENDING'),
                                                                          ('f7b4d964-67c3-40c8-84a6-687865fa6899', 'Fix Bug #123', 'Resolve critical bug in production', '2025-03-21', 'HIGH', 'IN_PROGRESS'),
                                                                          ('3dbb6ddf-f946-4c80-9e31-592b40ff3aa5', 'Prepare Presentation', 'Create slides for stakeholder meeting', '2025-03-27', 'MEDIUM', 'PENDING'),
                                                                          ('8b1624f0-c7f7-47b9-91c2-dbf3b2587a31', 'Optimize Database', 'Improve query performance', '2025-03-26', 'HIGH', 'COMPLETED'),
                                                                          ('e6e2c8bd-4e16-4b3a-b4b6-d6a4b2e0bb89', 'Schedule Meeting', 'Arrange call with client', '2025-03-24', 'MEDIUM', 'PENDING'),
                                                                          ('1c324ef9-6a98-4be5-8259-8f6c830f978a', 'Refactor Code', 'Improve maintainability', '2025-03-30', 'LOW', 'IN_PROGRESS'),
                                                                          ('c7a8b48e-9e3a-470d-85e2-bc9e5cd85b7b', 'Deploy Release v1.2', 'Push latest updates to production', '2025-03-29', 'HIGH', 'BLOCKED'),
                                                                          ('ab4ed4b6-1bfa-42a4-9269-6b1b1d18c8f3', 'Write Test Cases', 'Increase test coverage', '2025-04-02', 'MEDIUM', 'PENDING');

INSERT INTO dependency (task_id, dependency_id) VALUES
                                                    ('1c324ef9-6a98-4be5-8259-8f6c830f978a', '3dbb6ddf-f946-4c80-9e31-592b40ff3aa5'),
                                                    ('1c324ef9-6a98-4be5-8259-8f6c830f978a', '8b1624f0-c7f7-47b9-91c2-dbf3b2587a31'),
                                                    ('1c324ef9-6a98-4be5-8259-8f6c830f978a', 'd2c8b8a1-4f2b-42de-b69f-2c62f8bb32c9'),
                                                    ('8b1624f0-c7f7-47b9-91c2-dbf3b2587a31', '550e8400-e29b-41d4-a716-446655440000'),
                                                    ('c7a8b48e-9e3a-470d-85e2-bc9e5cd85b7b', 'c7a8b48e-9e3a-470d-85e2-bc9e5cd85b7b');

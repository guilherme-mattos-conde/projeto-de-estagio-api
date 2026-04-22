CREATE TABLE IF NOT EXISTS project (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    client_name VARCHAR(255) NOT NULL,
    city VARCHAR(100) NOT NULL,
    state CHAR(2) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    sheets_count INTEGER NOT NULL DEFAULT 0,
    status VARCHAR(50) NOT NULL,

    CONSTRAINT uk_project_name UNIQUE (name)
);

CREATE INDEX IF NOT EXISTS idx_project_client ON project (client_name);
CREATE INDEX IF NOT EXISTS idx_project_location ON project (city, state);
CREATE INDEX IF NOT EXISTS idx_project_status ON project (status);
CREATE INDEX IF NOT EXISTS idx_project_created_at ON project (created_at DESC);

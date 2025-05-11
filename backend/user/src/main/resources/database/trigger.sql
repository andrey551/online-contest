-- trigger auto set when update user
CREATE OR REPLACE FUNCTION update_updated_at_column()
RETURNS TRIGGER AS $$
BEGIN
    NEW.update_at = now(); -- Set updated_at to the current timestamp
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER update_example_table_updated_at
    BEFORE UPDATE ON contest_user
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at_column();

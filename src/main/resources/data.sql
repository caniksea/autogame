INSERT INTO transaction_type SELECT * FROM (
    SELECT '8djersdq8', 'Wager' UNION
    SELECT '3se342daw3', 'Win'
    ) temp WHERE NOT EXISTS (SELECT * FROM transaction_type);

INSERT INTO player SELECT * FROM (
    SELECT '001', 'test-gamer', 'Lil', 'John' UNION
    SELECT '002', 'shyrock', 'Kuf', 'Lin'
    ) temp WHERE NOT EXISTS (SELECT * FROM player);

INSERT INTO player_account SELECT * FROM (
    SELECT '001', 0 UNION
    SELECT '002', 0
    ) temp WHERE NOT EXISTS (SELECT * FROM player_account);

INSERT INTO apikey SELECT * FROM (
    SELECT 'Customer Support Key', '$2a$10$DbYaleCKVLPdnL2HtdW7JOF8q9yRWdfC12FvzVvBle/zf6UqP1Nce'
    ) temp WHERE NOT EXISTS (SELECT * FROM apikey);
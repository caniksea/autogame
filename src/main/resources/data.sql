INSERT INTO transaction_type SELECT * FROM (
    SELECT '8djersdq8', 'Wager' UNION
    SELECT '3se342daw3', 'Win'
    ) temp WHERE NOT EXISTS (SELECT * FROM transaction_type);

INSERT INTO player SELECT * FROM (
    SELECT '001', 'Lil', 'John', 'test-gamer' UNION
    SELECT '002', 'Kuf', 'Lin', 'shyrock'
    ) temp WHERE NOT EXISTS (SELECT * FROM player);

INSERT INTO player_account SELECT * FROM (
    SELECT '001', 0 UNION
    SELECT '002', 0
    ) temp WHERE NOT EXISTS (SELECT * FROM player_account);

INSERT INTO apikey SELECT * FROM (
    SELECT 'Customer Support Key', '$2a$10$DbYaleCKVLPdnL2HtdW7JOF8q9yRWdfC12FvzVvBle/zf6UqP1Nce'
    ) temp WHERE NOT EXISTS (SELECT * FROM apikey);

INSERT INTO promotion SELECT * FROM (
    SELECT 'paper', '2021-07-25T01:34:32', 'test promotion', '2021-11-10' , 5, '2021-11-01'
    ) temp WHERE NOT EXISTS (SELECT * FROM promotion);
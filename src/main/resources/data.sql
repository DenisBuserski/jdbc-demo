CREATE TABLE `managers` (
`id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
`username` VARCHAR(50)
);

INSERT INTO `managers` (`id`, `username`)
VALUES
    (1, 'Demon1'),
    (2, 'Demon2'),
    (3, 'Demon3');

CREATE TABLE `managers_employees` (
`manager_id` INT,
`employee_id` INT
);

INSERT INTO `managers_employees` (`manager_id`, `employee_id`)
VALUES
    (1, 1),
    (2, 2),
    (3, 3),
    (1, 4),
    (2, 5),
    (3, 6),
    (1, 7),
    (2, 8),
    (1, 9),
    (2, 10);
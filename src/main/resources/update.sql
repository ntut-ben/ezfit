ALTER TABLE `Product` ADD FULLTEXT INDEX `product_name_ft_index` (`name`) WITH PARSER ngram;

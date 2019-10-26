ALTER TABLE `Product` ADD FULLTEXT INDEX `product_name_ft_index` (`name`,`introduction`) WITH PARSER ngram;

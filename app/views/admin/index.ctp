<?php
echo $session->flash();
echo '<div>' . $html->link('添加供货商', '/providers/addProvider') . '</div>';
echo '<div>' . $html->link('添加使用指南', '/helps/addHelp') . '</div>';
echo '<div>' . $html->link('添加礼品', '/gifts/addGift') . '</div>';
echo '<div>' . $html->link('添加Facet', '/facets/addFacet') . '</div>';
echo '<div>' . $html->link('添加关键词', '/facets/addKeyword') . '</div>';
echo '<div>' . $html->link('批量更新向量', '/gifts/batchGenerateVectors') . '</div>';
?>
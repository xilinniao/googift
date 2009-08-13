<style type="text/css">
.leftMenu {
	float: left;
	background-color:#cccccc;
	height:600px;
}
</style>
<div class="leftMenu">
    <ul>
        <li><?php echo $html->link('添加供应商', '/users/addProvider'); ?></li>
        <li><?php echo $html->link('管理商品', '/users/manageGift'); ?></li>
    </ul>
</div>

<div class="mainContent">
    <table>
        <tr><th>编号</th><th>全称</th><th>操作</th></tr>
        <?php foreach ($providers as $provider) {?>
        <tr><td><?php echo $provider['id']; ?></td><td><?php echo $html->link($provider['full_name'], '/providers/showOneProvider?id=' . $provider['id']); ?></td><td><?php echo $html->link('删除', '/providers/delete?id=' . $provider['id']); ?></td></tr>
        <?php } ?>
    </table>
</div>

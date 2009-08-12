<div id="leftMenu">
    <ul>
        <li><?php echo $html->link('添加供应商', '/users/addProvider'); ?></li>
        <li><?php echo $html->link('管理商品', '/users/manageGift'); ?></li>
    </ul>
</div>

<div id="mainContent">
    <table>
        <tr><th>编号</th><th>全称</th><th>操作</th></tr>
        <?php foreach ($providers as $provider) {?>
        <tr><td><?php echo $provider['id']; ?></td><td><?php echo $html->link($provider['full_name'], '/providers/showOneProvider?id=' . $provider['id']); ?></td><td><?php echo $html->link('删除', '/providers/delete?id=' . $provider['id']); ?></td></tr>
        <?php } ?>
    </table>
</div>

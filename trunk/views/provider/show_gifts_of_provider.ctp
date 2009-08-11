<TABLE width="80%" border="thin">
    <tr>
        <th>编号</th><th>名称</th><th>操作</th>
    </tr>
<?php foreach ($gifts as $gift) {?>
    <tr>
        <td><?php echo $gift['id']; ?></td><td><?php echo $html->link($gift['name'], '/gifts/showOneGift?id=' . $gift['id']); ?></td><td><?php echo $html->link('删除', '/gifts/delete?id=' . $gift['id']); ?></td>
    </tr>
<?php } ?>
</TABLE>

document.addEventListener('DOMContentLoaded', function() {
    // 获取输入字段和错误消息元素
    const phnumberInput = document.getElementById('phnumber');
    const idcardInput = document.getElementById('idcard');
    const phnumberError = document.getElementById('phnumberError');
    const idcardError = document.getElementById('idcardError');

    // 定义验证函数
    function validatePhoneNumber(phoneNumber) {
        // 示例：中国手机号验证（11位数字，以1开头）
        const regex = /^1[3-9]\d{9}$/;
        return regex.test(phoneNumber);
    }

    function validateIdCard(idCardNumber) {
        // 示例：简单的中国身份证号验证（15位或18位数字，最后可能是X或x）
        const regex = /^\d{15}|\d{17}[\dXx]$/;
        return regex.test(idCardNumber);
    }

    // 为输入字段添加事件监听器
    phnumberInput.addEventListener('input', function() {
        const phoneNumber = this.value;
        if (validatePhoneNumber(phoneNumber)) {
            phnumberError.textContent = ''; // 清除错误消息
        } else {
            phnumberError.textContent = '请输入正确的手机号'; // 显示错误消息
        }
    });

    idcardInput.addEventListener('input', function() {
        const idCardNumber = this.value;
        if (validateIdCard(idCardNumber)) {
            idcardError.textContent = ''; // 清除错误消息
        } else {
            idcardError.textContent = '请输入正确的身份证号'; // 显示错误消息
        }
    });
});
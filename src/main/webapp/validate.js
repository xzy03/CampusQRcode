document.addEventListener('DOMContentLoaded', function() {
    // ��ȡ�����ֶκʹ�����ϢԪ��
    const phnumberInput = document.getElementById('phnumber');
    const idcardInput = document.getElementById('idcard');
    const phnumberError = document.getElementById('phnumberError');
    const idcardError = document.getElementById('idcardError');

    // ������֤����
    function validatePhoneNumber(phoneNumber) {
        // ʾ�����й��ֻ�����֤��11λ���֣���1��ͷ��
        const regex = /^1[3-9]\d{9}$/;
        return regex.test(phoneNumber);
    }

    function validateIdCard(idCardNumber) {
        // ʾ�����򵥵��й����֤����֤��15λ��18λ���֣���������X��x��
        const regex = /^\d{15}|\d{17}[\dXx]$/;
        return regex.test(idCardNumber);
    }

    // Ϊ�����ֶ�����¼�������
    phnumberInput.addEventListener('input', function() {
        const phoneNumber = this.value;
        if (validatePhoneNumber(phoneNumber)) {
            phnumberError.textContent = ''; // ���������Ϣ
        } else {
            phnumberError.textContent = '��������ȷ���ֻ���'; // ��ʾ������Ϣ
        }
    });

    idcardInput.addEventListener('input', function() {
        const idCardNumber = this.value;
        if (validateIdCard(idCardNumber)) {
            idcardError.textContent = ''; // ���������Ϣ
        } else {
            idcardError.textContent = '��������ȷ�����֤��'; // ��ʾ������Ϣ
        }
    });
});